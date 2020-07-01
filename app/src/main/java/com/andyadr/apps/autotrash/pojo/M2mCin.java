package com.andyadr.apps.autotrash.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class M2mCin implements Parcelable {

	@SerializedName("cs")
	private int cs;

	@SerializedName("ct")
	private String ct;

	@SerializedName("st")
	private int st;

	@SerializedName("con")
	private String con;

	@SerializedName("ty")
	private int ty;

	@SerializedName("ri")
	private String ri;

	@SerializedName("lt")
	private String lt;

	@SerializedName("pi")
	private String pi;

	@SerializedName("cnf")
	private String cnf;


	@SerializedName("rn")
	private String rn;


	public void setCs(int cs) {
		this.cs = cs;
	}

	public void setCt(String ct) {
		this.ct = ct;
	}

	public void setSt(int st) {
		this.st = st;
	}

	public void setCon(String con) {
		this.con = con;
	}

	public void setTy(int ty) {
		this.ty = ty;
	}

	public void setRi(String ri) {
		this.ri = ri;
	}

	public void setLt(String lt) {
		this.lt = lt;
	}

	public void setPi(String pi) {
		this.pi = pi;
	}

	public void setCnf(String cnf) {
		this.cnf = cnf;
	}

	public void setRn(String rn) {
		this.rn = rn;
	}

	public M2mCin(){

    }
	public int getCs(){
		return cs;
	}

	public String getCt(){
		return ct;
	}

	public int getSt(){
		return st;
	}

	public String getCon(){
		return con;
	}

	public int getTy(){
		return ty;
	}

	public String getRi(){
		return ri;
	}

	public String getLt(){
		return lt;
	}

	public String getPi(){
		return pi;
	}

	public String getCnf(){
		return cnf;
	}

	public String getRn(){
		return rn;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeInt(cs);
		parcel.writeString(ct);
		parcel.writeInt(st);
		parcel.writeString(con);
		parcel.writeInt(ty);
		parcel.writeString(ri);
		parcel.writeString(lt);
		parcel.writeString(pi);
		parcel.writeString(cnf);
		parcel.writeString(rn);
	}

	protected M2mCin(Parcel in) {
		cs = in.readInt();
		ct = in.readString();
		st = in.readInt();
		con = in.readString();
		ty = in.readInt();
		ri = in.readString();
		lt = in.readString();
		pi = in.readString();
		cnf = in.readString();
		rn = in.readString();
	}

	public static final Creator<M2mCin> CREATOR = new Creator<M2mCin>() {
		@Override
		public M2mCin createFromParcel(Parcel in) {
			return new M2mCin(in);
		}

		@Override
		public M2mCin[] newArray(int size) {
			return new M2mCin[size];
		}
	};

	@Override
	public String toString(){
		return
				"{con = "+con+"}";
	}

}