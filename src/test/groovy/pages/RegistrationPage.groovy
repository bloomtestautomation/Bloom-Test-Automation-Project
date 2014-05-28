import geb.*

class RegistrationPage extends Page {

    static url = "http://bloomtademo.apphb.com/en/register/index"
    static at = { title == "Registration" }
    static content = {
    	emailInput { $("input", id: "RegEmail") }
    	phoneInput { $("input", id: "RegPhone") }
    	newRegistrationButton { $("input", value: "New Registration") }
        openRegistrationButton { $("input", value: "Open Registration") }
    }

    def registerUser() {
		def email = timestamp() + "robot@example.com"
        def phone = createPhoneNumber()

    	emailInput.value email
    	phoneInput.value phone
    	newRegistrationButton.click()

        // return user creds for verification
    	[email:email, phone:phone]
    }

    def timestamp() {
    	def date = new Date()
		def timestamp = date.time.toString()
    }

    def createPhoneNumber() {
        def rt = timestamp()[0..9]
        rt[0..2] + "-" + rt[3..5] + "-" + rt[6..9]
    }

}