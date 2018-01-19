package com.ukefu.webim.web.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author ricy Site.java 2010-3-1
 * 
 */
@Entity
@Table(name = "uk_system_message")
@org.hibernate.annotations.Proxy(lazy = false)
public class SystemMessage implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 98876333686462570L;
	private String id ;
	
	private String name ;
	
	private String msgtype ;	//配置类型 ： 邮件 | 短信 
	
	private String smtpserver ;   
	private String smtpuser;
	private String smtppassword ;
	private String mailfrom ;
	private String seclev;   //ssl...
	private String orgi;
	
	private String smstype ;	//短信发送类型 ,SDK或者 URL提交方式
	private String url ;		//短信网关的URL ， 通过URL方式提交短信
	private String appkey ;		//短信网关的APPKEY
	private String appsec ;		//短信网关的 APPSEC
	private String sign ;		//短信网关的签名
	
	private Date createtime = new Date();	//创建时间
	
	private String tpcode ;		//短信网关的模板代码
	/**
	 * @return the id
	 */
	@Id
	@Column(length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	public String getSmtpserver() {
		return smtpserver;
	}
	public void setSmtpserver(String smtpserver) {
		this.smtpserver = smtpserver;
	}
	public String getSmtpuser() {
		return smtpuser;
	}
	public void setSmtpuser(String smtpuser) {
		this.smtpuser = smtpuser;
	}
	public String getSmtppassword() {
		return smtppassword;
	}
	public void setSmtppassword(String smtppassword) {
		this.smtppassword = smtppassword;
	}
	public String getMailfrom() {
		return mailfrom;
	}
	public void setMailfrom(String mailfrom) {
		this.mailfrom = mailfrom;
	}
	public String getSeclev() {
		return seclev;
	}
	public void setSeclev(String seclev) {
		this.seclev = seclev;
	}
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	public String getSmstype() {
		return smstype;
	}
	public void setSmstype(String smstype) {
		this.smstype = smstype;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAppkey() {
		return appkey;
	}
	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}
	public String getAppsec() {
		return appsec;
	}
	public void setAppsec(String appsec) {
		this.appsec = appsec;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getTpcode() {
		return tpcode;
	}
	public void setTpcode(String tpcode) {
		this.tpcode = tpcode;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}