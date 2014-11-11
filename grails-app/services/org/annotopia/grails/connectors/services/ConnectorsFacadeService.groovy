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
import org.codehaus.groovy.grails.web.json.JSONObject

/**
 * @author Paolo Ciccarese <paolo.ciccarese@gmail.com>
 */
class ConnectorsFacadeService {

	def connectorsManagerService;
	
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
