/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2007-2011 The OpenNMS Group, Inc.
 * OpenNMS(R) is Copyright (C) 1999-2011 The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is a registered trademark of The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * OpenNMS(R) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenNMS(R).  If not, see:
 *      http://www.gnu.org/licenses/
 *
 * For more information contact:
 *     OpenNMS(R) Licensing <license@opennms.org>
 *     http://www.opennms.org/
 *     http://www.opennms.com/
 *******************************************************************************/

package com.example.sample.drools;

import static org.opennms.core.utils.InetAddressUtils.addr;

import org.junit.Test;
import org.opennms.netmgt.EventConstants;
import org.opennms.netmgt.correlation.drools.DroolsCorrelationEngine;
import org.opennms.netmgt.model.events.EventBuilder;
import org.opennms.netmgt.xml.event.Event;
import org.opennms.test.mock.EasyMockUtils;


public class DependencyRulesTest extends CorrelationRulesTestCase {
    private EasyMockUtils m_mocks = new EasyMockUtils();
    
    @Test
    public void testEmbeddedTypesRules() throws Exception {
        testDependencyRules("embeddedTypesDependencyRules");
    }
    
    @Test
    public void testExternalTypesRules() throws Exception {
        testDependencyRules("externalTypesDependencyRules");
    }

	private void testDependencyRules(String engineName) {
		
		getAnticipator().reset();
		
		anticipate( createInitializedEvent( 1, 1 ) );
        
    	EventBuilder bldr = new EventBuilder( "serviceImpacted", "Drools" );
    	bldr.setNodeid( 1 );
    	bldr.setInterface( addr( "10.1.1.1" ) );
    	bldr.setService( "HTTP" );
    	bldr.addParam("CAUSE", 17 );

    	anticipate( bldr.getEvent() );
    	
    	bldr = new EventBuilder( "applicationImpacted", "Drools" );
    	bldr.addParam("APP", "e-commerce" );
    	bldr.addParam("CAUSE", 17 );
    	
    	anticipate( bldr.getEvent() );
        
    	bldr = new EventBuilder( "applicationImpacted", "Drools" );
    	bldr.addParam("APP", "hr-portal" );
    	bldr.addParam("CAUSE", 17 );
    	
    	anticipate( bldr.getEvent() );
        
		DroolsCorrelationEngine engine = findEngineByName(engineName);

        Event event = createNodeLostServiceEvent( 1, "10.1.1.1", "ICMP" );
        event.setDbid(17);
        System.err.println("SENDING nodeLostService EVENT!!");
		engine.correlate( event );
		
		getAnticipator().verifyAnticipated();
		
    	bldr = new EventBuilder( "serviceRestored", "Drools" );
    	bldr.setNodeid( 1 );
    	bldr.setInterface( addr( "10.1.1.1" ) );
    	bldr.setService( "HTTP" );
    	bldr.addParam("CAUSE", 17 );

    	anticipate( bldr.getEvent() );
    	
    	bldr = new EventBuilder( "applicationRestored", "Drools" );
    	bldr.addParam("APP", "e-commerce" );
    	bldr.addParam("CAUSE", 17 );
    	
    	anticipate( bldr.getEvent() );
        
    	bldr = new EventBuilder( "applicationRestored", "Drools" );
    	bldr.addParam("APP", "hr-portal" );
    	bldr.addParam("CAUSE", 17 );
    	
    	anticipate( bldr.getEvent() );
		
        event = createNodeRegainedServiceEvent( 1, "10.1.1.1", "ICMP" );
        event.setDbid(18);
        System.err.println("SENDING nodeRegainedService EVENT!!");
		engine.correlate( event );
		
		getAnticipator().verifyAnticipated();

	}
    
    private Event createInitializedEvent(int symptom, int cause) {
        return new EventBuilder("initialized", "Drools").getEvent();
    }

    // Currently unused
//    private Event createRootCauseEvent(int symptom, int cause) {
//        return new EventBuilder(createNodeEvent("rootCauseEvent", cause)).getEvent();
//    }


    public Event createNodeDownEvent(int nodeid) {
        return createNodeEvent(EventConstants.NODE_DOWN_EVENT_UEI, nodeid);
    }
    
    public Event createNodeUpEvent(int nodeid) {
        return createNodeEvent(EventConstants.NODE_UP_EVENT_UEI, nodeid);
    }
    
    public Event createNodeLostServiceEvent(int nodeid, String ipAddr, String svcName)
    {
    	return createSvcEvent("uei.opennms.org/nodes/nodeLostService", nodeid, ipAddr, svcName);
    }
    
    public Event createNodeRegainedServiceEvent(int nodeid, String ipAddr, String svcName)
    {
    	return createSvcEvent("uei.opennms.org/nodes/nodeRegainedService", nodeid, ipAddr, svcName);
    }
    
    private Event createSvcEvent(String uei, int nodeid, String ipaddr, String svcName)
    {
    	return new EventBuilder(uei, "Drools")
    		.setNodeid(nodeid)
    		.setInterface( addr( ipaddr ) )
    		.setService( svcName )
    		.getEvent();
    		
    }

    private Event createNodeEvent(String uei, int nodeid) {
        return new EventBuilder(uei, "test")
            .setNodeid(nodeid)
            .getEvent();
    }
    



}