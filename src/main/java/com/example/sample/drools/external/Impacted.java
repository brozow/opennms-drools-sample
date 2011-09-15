package com.example.sample.drools.external;

import org.opennms.netmgt.xml.event.Event;

public class Impacted {

	private Object m_target;
	private Event m_cause;
	
	public Impacted() {}
	
	public Impacted(Object target, Event cause)
	{
		m_target = target;
		m_cause = cause;
	}
	
	public Object getTarget() {
		return m_target;
	}
	public void setTarget(Object target) {
		m_target = target;
	}
	public Event getCause() {
		return m_cause;
	}
	public void setCause(Event cause) {
		m_cause = cause;
	}

	@Override
	public String toString() {
		return "Impacted[ target=" + m_target + ", cause=" + m_cause + " ]";
	}
	
	

}