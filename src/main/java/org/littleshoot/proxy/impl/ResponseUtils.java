package org.littleshoot.proxy.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

import java.nio.charset.Charset;

public class ResponseUtils {

	/**
	 * Factory for {@link DefaultFullHttpResponse}s.
	 * 
	 * @param httpVersion
	 * @param status
	 * @param body
	 * @return
	 */
	public static DefaultFullHttpResponse responseFor(HttpVersion httpVersion, HttpResponseStatus status, String body) {
		byte[] bytes = body.getBytes(Charset.forName("UTF-8"));
		ByteBuf content = Unpooled.copiedBuffer(bytes);
		return responseFor(httpVersion, status, content, bytes.length);
	}

	/**
	 * Factory for {@link DefaultFullHttpResponse}s.
	 * 
	 * @param httpVersion
	 * @param status
	 * @return
	 */
	public static DefaultFullHttpResponse responseFor(HttpVersion httpVersion, HttpResponseStatus status) {
		return responseFor(httpVersion, status, (ByteBuf) null, 0);
	}

	/**
	 * Factory for {@link DefaultFullHttpResponse}s.
	 * 
	 * @param httpVersion
	 * @param status
	 * @param body
	 * @param contentLength
	 * @return
	 */
	private static DefaultFullHttpResponse responseFor(HttpVersion httpVersion, HttpResponseStatus status, ByteBuf body, int contentLength) {
		DefaultFullHttpResponse response = body != null ? new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status, body) : new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status);
		if (body != null) {
			response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, contentLength);
			response.headers().set("Content-Type", "text/html; charset=UTF-8");
		}
		return response;
	}

}