from selenium import webdriver
a_website = "https://www.google.com"

driver = webdriver.Chrome()
driver.get('https://www.facebook.com/?_rdc=2&_rdr')

username = 'oibearkoko'
password = 'test22zz'

driver.find_element_by_id('email').send_keys(username)
driver.find_element_by_id('pass').send_keys(password)
driver.find_element_by_id('loginbutton').click()