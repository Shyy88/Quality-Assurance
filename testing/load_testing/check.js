import { check, fail } from "k6";

export function checkStatus({ response, expectedStatus, expectContent, failOnError, printOnError, dynamicIds }) {
  // console warning if empty status
  if (isEmpty(expectedStatus) && isEmpty(expectContent)) {
    console.warn('No expected status or content specified in call to checkStatus for URL ' + response.url);
    return;
  }

  let contentCheckResult;
  let statusCheckResult;

  let url = response.url;

  if (dynamicIds) {
    dynamicIds.forEach((dynamicId) => {
      if (response.url.includes(dynamicId)) {
        url = url.replace(dynamicId, '[id]');
      }
    });
  }

  if (expectContent) {
    contentCheckResult = check(response, {
      [`"${expectContent}" in ${url} response`]: (r) => r.body.includes(expectContent),
    });
  }

  if (expectedStatus) {
    const obj = {};
    obj[`${response.request.method} ${url} status ${expectedStatus}`] = (r) => r.status === expectedStatus;

    statusCheckResult = check(response, obj);
  }
  // check status result 
  if (!statusCheckResult || !contentCheckResult && expectContent) {
    if (printOnError && response.body) {
      console.log("Unexpected response: " + response.body);
    }
    if (failOnError) {
      if (!statusCheckResult && (!contentCheckResult && expectContent)) {
        fail(`${response.request.method} ${url} status ${expectedStatus} and "${expectContent}" not found in response`);
      } else {
        if (!statusCheckResult) {
          fail(`Received unexpected status code ${response.status} for URL: ${url}, expected ${expectedStatus}`);
        } else if (!contentCheckResult) {
          fail(`"${expectContent}" not found in response for URL: ${url}`);
        }
      }
    }
  }
}

// if empty function
function isEmpty(str) {
  return (!str || str.length === 0);
}