import geb.*

class AdminRegistrationPage extends Page {

	static url = "http://bloomtademo.apphb.com/admin/RegAdmin"
	static at = { title == "Registration Admin" }
	static content = {
		searchField { $("input", class:"search") }
		edit { $("i", class:"fi-pencil") }
		id { $("td", class:"id") }
	}

	def findRegistration(userCreds) {
		searchField.value userCreds.get("email")
		edit.click()
	}

	def findRegistrationParticipants(userCreds) {
		searchField.value userCreds.get("email")
		id.children().click()
	}

	def verifyPriceTotal(price) {
		$("td", text: contains("Registration Price")).next().text().contains(price)
	}           

	def verifyRegistration(status) {

		// Geb docs are incorrect regarding checkboxes!  See stackoverflow link:
		// http://stackoverflow.com/questions/19766047/gebish-checkbox-not-getting-checked
		if ( status == "confirmed" ) {
			$("input", type:"checkbox", id:"IsConfirmed").value() == "true"
		} else if ( status == "unconfirmed" ) {
			$("input", type:"checkbox", id:"IsConfirmed").value() == false
		} else {
			println "Function missing expected arguments - complete, incomplete."
		}

	}

}