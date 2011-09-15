package com.example.sample.drools;

public class DependsOn {
	private Object m_a;
	private Object m_b;
	
	public DependsOn() {}
	
	public DependsOn(Object a, Object b)
	{
		m_a = a;
		m_b = b;
	}

	public Object getA() {
		return m_a;
	}

	public void setA(Object a) {
		m_a = a;
	}

	public Object getB() {
		return m_b;
	}

	public void setB(Object b) {
		m_b = b;
	}
	
	@Override
	public String toString() {
		return "DependsOn[ a=" + m_a + ", b=" + m_b + " ]";
	}
	
	
}
