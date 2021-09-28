package com.project.spring.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@RequestMapping("/echo")
public class EchoHandler extends TextWebSocketHandler{
	
	private static List<WebSocketSession> sessionList = new ArrayList<>();
	
	//Client Connection
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
		System.out.println("=========> Socket Connection");
		
		sessionList.add(session);
	}
	
	// Client --- Message ---> WebSocket
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		for(WebSocketSession sess : sessionList) {
			sess.sendMessage(new TextMessage("userName" + " : " + message.getPayload()));
		}
	}
	
	// Client DisConnection
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		
		System.out.println("=========> Socket DisConnection");
		
		for(WebSocketSession sess : sessionList) {
			sess.sendMessage(new TextMessage("userName" + "님의 연결이 끊어졌습니다."));
		}
		
		sessionList.remove(session);
	
	}

}
