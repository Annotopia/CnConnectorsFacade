import grails.util.Metadata
grails.app.context="/"

// configuration for plugin testing - will not be included in the plugin zip

// Necessary for Grails 2.0 as the variable ${appName} is not available
// anymore in the log4j closure. It needs the import above.
def appName = Metadata.current.getApplicationName();

grails.config.locations = ["classpath:${appName}-config.properties", "file:./${appName}-config.properties"]

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

environments {
	development {
		grails.logging.jul.usebridge = true
	}
	production {
		grails.logging.jul.usebridge = false
		// TODO: grails.serverURL = "http://www.changeme.com"
	}
}

environments {
	development {
		log4j = {
			appenders {
				console name:'stdout', threshold: org.apache.log4j.Level.ALL,
					layout:pattern(conversionPattern: '%d{mm:ss,SSS} %5p %c{1} %m%n')
			}

		    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
		           'org.codehaus.groovy.grails.web.pages', //  GSP
		           'org.codehaus.groovy.grails.web.sitemesh', //  layouts
		           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
		           'org.codehaus.groovy.grails.web.mapping', // URL mapping
		           'org.codehaus.groovy.grails.commons', // core / classloading
		           'org.codehaus.groovy.grails.plugins', // plugins
		           'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
		           'org.springframework',
		           'org.hibernate',
		           'net.sf.ehcache.hibernate'
		
		    warn   'org.mortbay.log'
			
			info   'grails.app'
		}
	}
	
	test {
		log4j = {
			appenders {
				console name:'stdout', threshold: org.apache.log4j.Level.ALL,
					layout:pattern(conversionPattern: '%d{mm:ss,SSS} %5p %c{1} %m%n')
			}

		    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
		           'org.codehaus.groovy.grails.web.pages', //  GSP
		           'org.codehaus.groovy.grails.web.sitemesh', //  layouts
		           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
		           'org.codehaus.groovy.grails.web.mapping', // URL mapping
		           'org.codehaus.groovy.grails.commons', // core / classloading
		           'org.codehaus.groovy.grails.plugins', // plugins
		           'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
		           'org.springframework',
		           'org.hibernate',
		           'net.sf.ehcache.hibernate'
		
		    warn   'org.mortbay.log'
			
			info   'grails.app'
			
			trace  'org.annotopia.grails.connectors',
				   'grails.app.services.org.annotopia.grails.connectors.services.ConnectorsFacadeService',
				   'grails.app.services.org.annotopia.grails.connectors.services.ConnectorsManagerService',
				   'grails.app.services.org.annotopia.grails.connectors.plugin.bioportal.services.BioPortalService',
				   'grails.app.services.org.annotopia.grails.connectors.plugin.bioportal.services.BioPortalTextMiningService',
				   'grails.app.services.org.annotopia.grails.connectors.plugin.bioportal.services.JsonBioPortalAnnotatorResultsConverterV0Service',
			       'org.annotopia.grails.connectors.services.tests'
		}
	}
}
