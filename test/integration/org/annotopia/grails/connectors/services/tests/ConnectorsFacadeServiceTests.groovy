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
package org.annotopia.grails.connectors.services.tests;

import static org.junit.Assert.*
import grails.test.mixin.TestFor

import org.annotopia.grails.connectors.services.ConnectorsFacadeService
import org.junit.Test

/**
 * @author Paolo Ciccarese <paolo.ciccarese@gmail.com>
 */
@TestFor(ConnectorsFacadeService)
class ConnectorsFacadeServiceTests extends GroovyTestCase {

	def connectorsFacadeService;
	
	private LOG_SEPARATOR() {
		logSeparator('=' as char);
	}
	
	private logSeparator(char c) {
		if (c==null) c = '=';
		log.info(stringOf(c as char, (68) as int))
	}
	
	private final StringBuilder builder = new StringBuilder();
	private String stringOf( char c , int times ) {
		for( int i = 0 ; i < times ; i++  ) {
			builder.append( c );
		}
		String result = builder.toString();
		builder.delete( 0 , builder.length() -1 );
		return result;
	}
	
	@Test
	public void testListVocabularies() {
		log.info 'TEST ------------- testListVocabularies -------------'
		def vocabularies = connectorsFacadeService.listVocabularies("cnBioPortalConnector", new HashMap());
		assertNotNull vocabularies;
	}

}
