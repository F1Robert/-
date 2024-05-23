package com.nolan.bean;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.net.DatagramPacket;
import java.net.InetAddress;

public class CommDataInfo {
    InetAddress devAddr;
    int port;
    public int dataLen;
    public byte[] data;

    public CommDataInfo(CommDataInfo info) {
        this.devAddr = info.devAddr;
        this.port = info.port;
    }

    public CommDataInfo(DatagramPacket recvPkg) {
        this.devAddr = recvPkg.getAddress();
        this.port = recvPkg.getPort();
        this.dataLen = recvPkg.getLength();
        this.data = recvPkg.getData();
    }

    public CommDataInfo(String targetIP, int targetPort) {
        try {
            this.devAddr = InetAddress.getByName(targetIP);
        } catch (Exception var4) {
            this.devAddr = null;
        }

        this.port = targetPort;
    }

    public CommDataInfo(InetAddress targetIP, int targetPort) {
        this.devAddr = targetIP;
        this.port = targetPort;
    }

    public CommDataInfo(InetAddress targetIP, int targetPort, byte[] senddata) {
        this.devAddr = targetIP;
        this.port = targetPort;
        this.data = senddata;
        this.dataLen = senddata.length;
    }

    public CommDataInfo(byte[] senddata, int len) {
        this.data = senddata;
        this.dataLen = len;
    }

    public CommDataInfo(byte[] senddata) {
        this.data = senddata;
        this.dataLen = senddata.length;
    }

    public boolean Compare(CommDataInfo targetInfo) {
        return targetInfo.port == this.port && targetInfo.devAddr == this.devAddr;
    }

    public String GetIPAddress() {
        return this.devAddr.getHostAddress();
    }

    public int GetPort() {
        return this.port;
    }

    public void ClearData() {
        if (this.data != null && this.data.length > 1) {
            this.data[0] = -27;
            this.data[1] = -88;
        }

    }

    public void SetAddress(InetAddress targetIP, int targetPort) {
        this.devAddr = targetIP;
        this.port = targetPort;
    }

    public void SetAddress(CommDataInfo commDataInfo) {
        this.devAddr = commDataInfo.devAddr;
        this.port = commDataInfo.port;
    }

    public void SetData(byte[] senddata) {
        this.data = senddata;
        this.dataLen = senddata.length;
    }

    public void SetData(byte[] senddata, int length) {
        this.data = senddata;
        this.dataLen = length;
    }

    public void SetData(String strJson) {
        byte[] senddata = strJson.getBytes();
        this.data = senddata;
        this.dataLen = senddata.length;
    }

}