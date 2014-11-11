
class BootStrap {

	def grailsApplication
	def connectorsManagerService
   
    def init = { servletContext ->		
		//---------------------------------------
		// CONNECTORS
		//---------------------------------------
		demarcation(">> CONNECTORS EXTENSION INTERFACES");
		connectorsManagerService.registerInterfaces();
		demarcation(">> CONNECTORS DETECTION");
		connectorsManagerService.registerConnectors();
		demarcation(">> Bootstrapping completed!")
		separator()
    }
	
	private demarcation() {
		log.info  '========================================================================';
	}
	private demarcation(message) {
		demarcation();
		log.info  message
	}
	private separator() {
		log.info  '------------------------------------------------------------------------';
	}
	private separator(message) {
		separator();
		log.info  message
	}
	
    def destroy = {
    }
}
