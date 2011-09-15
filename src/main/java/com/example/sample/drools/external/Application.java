package com.example.sample.drools.external;

public class Application {
	private String m_name;
	
	public Application() {}
	
	public Application(String name)
	{
		m_name = name;
	}

	public String getName() {
		return m_name;
	}

	public void setName(String name) {
		m_name = name;
	}
	
	public DependsOn dependsOn(Object o)
	{
		return new DependsOn(this, o);
	}

	@Override
	public String toString() {
		return "Application[ name=" + m_name + " ]";
	}
	
}
