package com.example.demo.objects;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import com.example.demo.apiParameter.SendToAddressRequest;
import static com.example.demo.constant.Constant.*;
import lombok.Data;

@Data
@Entity
@IdClass(PkTxidCategoryToAddr.class)
public class TbTrans implements Serializable {

	@Temporal(TemporalType.TIMESTAMP)
	@Generated(GenerationTime.INSERT)
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable=false, updatable=false)
	private Date regDt;
	@Id
    private String txid;
	@Id
	private String category;
	@Id
	private String toAddr;
	@Column(nullable=false)
	private String uid;
	private long confirm;
	private String fromAddr;
	private String myAddr;
	private double amount;
	private char reNotify = 'N';
	private char notifiable = 'Y';
	private int notiCnt;
	private long txTime;
	private String txTimeStr;
	private String fromTag;
	private String toTag;
	private String blockId;
	private double fee;
	private String error;
	private String txIdx;
	private String orderId;

	public TbTrans() { }

	public TbTrans(SendToAddressRequest req) {
		this.uid       	= req.getUid();
		this.category   = CATEGORY_SEND;
		this.toAddr    	= req.getToAddress();
		this.amount    	= req.getAmount();
	}

	public TbTrans(String category, String txid, String uid,
				   String toAddr, double amount) {
		this.category   = category;
		this.txid		= txid;
		this.uid		= uid;
		this.toAddr		= toAddr;
		this.amount		= amount;
	}

	// case of send
	public TbTrans(String txid, String uid, String toAddr, double amount) {
		this.category   = CATEGORY_SEND;
		this.txid		= txid;
		this.uid		= uid;
		this.toAddr		= toAddr;
		this.amount		= amount;
	}

	public TbTrans(String uid, String toAddr, double amount) {
		this.uid       = uid;
		this.toAddr    = toAddr;
		this.amount    = amount;
	}

}
