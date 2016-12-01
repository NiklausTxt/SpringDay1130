package com.nik.di.autowired;

public class XmlBean {
	private XmlWiredBean xmlWiredBean;

	public void print() {
		System.out.println("This is printring from XmlBean!");
	}

	public void printWiredBean() {
		xmlWiredBean.print();
	}

	public void setXmlWiredBean(XmlWiredBean xmlWiredBean) {
		this.xmlWiredBean = xmlWiredBean;
	}
}
