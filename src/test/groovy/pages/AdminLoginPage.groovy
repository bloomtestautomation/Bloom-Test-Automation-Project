import geb.*

class AdminLoginPage extends Page {

	static url = "http://bloomtademo.apphb.com/admin/Account/Login"
	static at = { title == "Site Admin Login" }
	static content = {
		userName { $("input", id:"UserName") }
		password { $("input", id:"Password") }
		logInButton { $("input", value:"Log in") }
	}

	def adminLogIn() {
		userName.value "admin"
		password.value "bloomta1"
		logInButton.click()
	}

}