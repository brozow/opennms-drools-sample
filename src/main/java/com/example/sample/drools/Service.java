package com.example.sample.drools;

import static org.opennms.core.utils.InetAddressUtils.addr;

import java.net.InetAddress;

public class Service {
	private long m_node;
	private InetAddress m_addr;
	private String m_svc;
	
	public Service() {}
	
	public Service(long node, String addrString, String svc) {
		m_node = node;
		m_addr = addr( addrString );
		m_svc = svc;
	}

	public Service(long node, InetAddress addr, String svc) {
		m_node = node;
		m_addr = addr;
		m_svc = svc;
	}

	public long getNode() {
		return m_node;
	}
	public void setNode(long node) {
		m_node = node;
	}
	public InetAddress getAddr() {
		return m_addr;
	}
	public void setAddr(InetAddress addr) {
		m_addr = addr;
	}
	public String getSvc() {
		return m_svc;
	}
	public void setSvc(String svc) {
		m_svc = svc;
	}

	@Override
	public String toString() {
		return "Service[ node=" + m_node + ", addr=" + m_addr + ", svc="
				+ m_svc + " ]";
	}

	
}
