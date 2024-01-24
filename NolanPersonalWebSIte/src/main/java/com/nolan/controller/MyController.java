package com.nolan.controller;

import org.junit.Test;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

@RestController
public class MyController {

    @PostMapping("/api/postData")
    public String postData(@RequestBody String data) {
        try {
            // 处理请求数据的逻辑
            System.err.println("Error in processing data: " + data);
            return "Success";
        } catch (Exception e) {
            // 发生错误时打印错误信息
            System.err.println("Error in processing data: " + e.getMessage());
            return "Error";
        }
    }

    @Test
    public void TempTest(){
        String plaintext = "{\"datas\":[{\"checkTime\":\"2023-11-29 09:14\",\"companyCode\":\"huaweiVpn\",\"deviceName\":\"Test Watch B7\",\"deviceType\":9,\"inputSources\":\"27\",\"itemKey\":\"HR\",\"items\":[{\"itemKey\":\"HR_CONTINUOUS\",\"itemName\":\"连续心率\",\"itemUnit\":\"次/分钟\",\"itemValue\":\"90\"},{\"itemKey\":\"HR_ENDTIME\",\"itemName\":\"连续心率结束时间\",\"itemUnit\":\"mm:dd\",\"itemValue\":\"1701220471102\"},{\"itemKey\":\"HR_RESTING\",\"itemName\":\"静息心率\",\"itemUnit\":\"次/分钟\",\"itemValue\":\"90\"},{\"itemKey\":\"HR_LATEST\",\"itemName\":\"最新心率值\",\"itemUnit\":\"次/分钟\",\"itemValue\":\"90\"},{\"itemKey\":\"HR_LATESTTIME\",\"itemName\":\"最新心率的时间\",\"itemUnit\":\"mm:dd\",\"itemValue\":\"1701220200000\"},{\"itemKey\":\"HR_MAX\",\"itemName\":\"最大心率\",\"itemUnit\":\"次/分钟\",\"itemValue\":\"111\"},{\"itemKey\":\"HR_MIN\",\"itemName\":\"最小心率\",\"itemUnit\":\"次/分钟\",\"itemValue\":\"81\"}]}],\"mobile\":\"18682207964\"}";
        String encryptedText = encryptAes(plaintext);
        String input = "1701223645168sbrM2yCdaB6H1S0L3L1f01w+iBeEX1PX8pdXI9eFyiVR8QTVV7lgYZ62vDQEsk29jkfDU/qyh1CjParIr6j3Rws8+n4D6moJxBKczVP7eo4dqjFRjWB8QYwfoaP2ZMUmxY/cI5OSlyl3hLTXMA8iVPR2VVqbJst5RUZ28TngoPmhKxUplSm8gLqboWrYFWKt9e50f+fVCiJMY8i7NF3/kz0USd4D7FihsseNmrJBqYQ+d8oSAjRaUyJqdplFtNdwVvjufwmGKauCUY/5LQTxYuIxBnt0d+S7A3P7Z3PqNYXEOe1y6+b3+ijl06sQKRx6L6wLmDAyQA4BCczLiZGCf0cWehwNU+PeQRLpQ/dI/SwjDl4c8iYPq+cMOaLSKUu5L06ZyautBpiwRhUVl7GTUT0mWG+DAGE7gMmtrpviD1RTSbGehl92z0NePo2NRY9dvgdGmCXySUJJFRZMet0JnCKMuG40ZMe8n5q8SuKsAJtu8uuDrYfPdtUnSHuNQB1uRmgsp0A1e7tnwpxyeqdS8ApBgJlYs/iXzC4v+HbmiU4IZnbAzS0Vlw4RraFTcyeH82aIlHKFopURWU2UOgy0ZAicO1yYpnC9+ABv0e0Affe+MRcZL5Vee7CgAMjDm89gkDjANAZK88V08VdYRvuCxKoSI0dgNPmerB6JVzmaSYH4OGQ7ONMUc5FBDphmvICkzQ7kT0UUGlZm9DrwWL7+3S8KPmg8acZ7QII6jqVGz1vbg5nVtAJQAEEN6BOxgJeHMCziN7YLCFwry4GG5iCHxEJo/6IsBLty7OGlpP9VmH4e0kP7+OC0PJa+ktofmK9fxkMx2MruHIFwnvFLlxwVnf3KagIOnfU8c3QsCJsmC/L25nYFFydHgemakDRgfpCTzvTrmrox1n7PjT21bUENmPsvFmrx8Ab+L/NWVFJJYQCjS/JzbWtMdDjTDTcaD8MqwjxV+6d9VaUQiQ7VQ50FYEgLRvEfi8WoNHa/2jId5lQ86tI/KCktDjEom/7QIroqahaJtYVa/nCB8tUqwvjGnp/50uxrNvObwaRr82LIjqQnBBI377jXMdF2T5f/P56gQKZOEySGbQ8yWKa0fxBAPrBqnagmpWMuoTfQn4jUO0E68hDk/fisvYU9+kVaK0tRsh23an235y55uhan";
        String md5Hash = calculateMD5(input);
        System.out.println("Encrypted Text:=" +encryptedText);
        System.out.println("MD5 Hash: ="+md5Hash);
    }

    public static String encryptAes(String str) {
        try {
            SecretKeySpec spec = new SecretKeySpec("sh23an235y55uhan".getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, spec);
            byte[] encrypted = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
            return new String(Base64.getEncoder().encode(encrypted), StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println("encryptAes: ");
        }
        return "";
    }

    public static String calculateMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));

            // Convert the byte array to a hexadecimal string
            StringBuilder result = new StringBuilder();
            for (byte b : digest) {
                result.append(String.format("%02x", b));
            }
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}


