package com.andyadr.apps.autotrash.pojopost;

import com.google.gson.annotations.SerializedName;

public class M2mCin{

	@SerializedName("con")
	private String con;

	public void setCon(String con){
		this.con = con;
	}

	public String getCon(){
		return con;
	}

	@Override
 	public String toString(){
		return
			"M2mCin{" +
			"con = '" + con + '\'' +
			"}";
		}
}