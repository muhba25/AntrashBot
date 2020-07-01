package com.andyadr.apps.autotrash.pojopost;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("m2m:cin")
	@Expose
	private M2mCin m2mCin;

	public void setM2mCin(M2mCin m2mCin){
		this.m2mCin = m2mCin;
	}

	public M2mCin getM2mCin(){
		return m2mCin;
	}

	@Override
 	public String toString(){
		return
			"Response{" +
			"m2m:cin = '" + m2mCin + '\'' +
			"}";
		}
}