/*
 * Copyright 2014 Massachusetts General Hospital
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.annotopia.grails.connectors.services

import org.annotopia.grails.connectors.ITermSearchService
import org.annotopia.grails.connectors.ITextMiningService
import org.annotopia.grails.connectors.IVocabulariesListService
import org.codehaus.groovy.grails.web.json.JSONArray
import org.codehaus.groovy.grails.web.json.JSONObject

/**
 * @author Paolo Ciccarese <paolo.ciccarese@gmail.com>
 */
class ConnectorsFacadeService {

	def connectorsManagerService;
	
	/**
	 * Lists all the available connectors interfaces.
	 * @param parameters	Constraints used to select the items
	 * @return All the available connector interfaces.
	 */
	JSONArray listConnectorsInterfaces(HashMap parameters) {
		def connectorsInterfaces = connectorsManagerService.listConnectorsInterfaces();
		JSONArray interfaces = new JSONArray();
		connectorsInterfaces.each { interf ->
			JSONObject s = new JSONObject();
			s.put("name", interf.getName());
			s.put("fullname", interf.getFullname());
			s.put("title", interf.getTitle());
			s.put("description", interf.getDescription());
			interfaces.put(s);
		}
		interfaces
	}
	
	/**
	 * List of available connectors. Constraints to the query can be 
	 * defined through the parameters.
	 * @param parameters	The constraints for the query.
	 * @return The list of available connectors.
	 */
	JSONArray listConnectors(HashMap parameters) {
		def connectors = connectorsManagerService.listConnectors();
		JSONArray cs = new JSONArray();
		connectors.each {
			JSONObject c = new JSONObject();
			c.put("ver", it.ver);
			c.put("name", it.name);
			c.put("title", it.title);
			c.put("description", it.description);
			c.put("serviceName", it.serviceName);
			
			JSONArray services = new JSONArray();
			it.interfaces.each { interf ->
				JSONObject s = new JSONObject();
				s.put("name", interf.getName());
				s.put("fullname", interf.getFullname());
				s.put("title", interf.getTitle());
				s.put("description", interf.getDescription());
				services.put(s);
			}	
			c.put("implements", services);			
			cs.put(c);
		}
		return cs;
	}
	
	/**
	 * Method that must be implemented by all term search services
	 * @param serviceName	The service to call
	 * @param content		The search query
	 * @param parameters	The service parametrization
	 * @return The results in JSON format
	 */
	JSONObject search(String serviceName, String content, HashMap parameters) {
		def service = connectorsManagerService.retrieveServiceFeature(serviceName, ITermSearchService.class.getName());
		service.search(content, parameters);
	}
	
	/**
	 * Method that returns all available vocabularies.
	 * @param serviceName	The service to call
	 * @param parameters	The service parametrization
	 * @return List of vocabularies
	 */
	JSONObject listVocabularies(Object serviceName, HashMap parameters) {
		def service = connectorsManagerService.retrieveServiceFeature(serviceName, IVocabulariesListService.class.getName());
		service.listVocabularies(parameters);
	}
	
	/**
	 * Method that must be implemented by all text mining and entity recognition services
	 * @param serviceName		The service to call
	 * @param resourceUri		The URI of the analyzed resource
	 * @param content			The content to analyze
	 * @param parameters		The service parametrization
	 * @return The results in JSON format
	 */
	JSONObject textmine(Object serviceName, String resourceUri, String content, HashMap parameters) {
		def service = connectorsManagerService.retrieveServiceFeature(serviceName, ITextMiningService.class.getName());
		service.textmine(resourceUri, content, parameters);
	}
}
