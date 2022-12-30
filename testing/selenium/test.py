from selenium import webdriver
import unittest
import time

class login_form_test(unittest.TestCase):

    @classmethod
    def setUpClass(cls):
        cls.browser = webdriver.Chrome(r'file/to/driver/if/needed/otherwise/remove/r')
        cls.browser.maximize_window()

    def test_login_github(self):
        time.sleep(1)
        self.browser.get('https://github.com/login')
        self.browser.find_element_by_id('login_field').send_keys('username')
        self.browser.find_element_by_id('password').send_keys('password')
        self.browser.find_element_by_class_name('btn-block').click()
     
   
    