package com.f.wx.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("xml")
public class MessageXML {
    @XStreamAlias("ToUserName")
    private String ToUserName;

    @XStreamAlias("FromUserName")
    private String FromUserName;

    @XStreamAlias("CreateTime")
    private long CreateTime;
    @XStreamAlias("Content")
    private String Content;

    @XStreamAlias("MsgId")
    private long MsgId;

    @XStreamAlias("MsgType")
    private String MsgType;

    @XStreamAlias("Event")
    private String Event;

    @XStreamAlias("EventKey")
    private String EventKey;

    @XStreamAlias("Ticket")
    private String Ticket;

    @XStreamAlias("Latitude")
    private String Latitude;

    @XStreamAlias("Longitude")
    private String Longitude;

    @XStreamAlias("Precision")
    private String Precision;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }

    public String getTicket() {
        return Ticket;
    }

    public void setTicket(String ticket) {
        Ticket = ticket;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getPrecision() {
        return Precision;
    }

    public void setPrecision(String precision) {
        Precision = precision;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public long getMsgId() {
        return MsgId;
    }

    public void setMsgId(long msgId) {
        MsgId = msgId;
    }

    public MessageXML(String toUserName, String fromUserName, long createTime, String content, String msgType) {
        ToUserName = toUserName;
        FromUserName = fromUserName;
        CreateTime = createTime;
        Content = content;
        MsgType = msgType;
    }

    public static MessageXML buildTxtMsg(String toUserName, String fromUserName,String content){
        return new MessageXML(fromUserName,toUserName,System.currentTimeMillis(),content,"text");
    }
}
