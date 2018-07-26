package com.young.coal.business.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessTip {

	private int code;
	private String message;
	private Object data;

	public SuccessTip(){
		this.code = 200;
		this.message = "操作成功";
	}

	public SuccessTip(int code, String message){
		this.code = code;
		this.message = message;
	}
}
