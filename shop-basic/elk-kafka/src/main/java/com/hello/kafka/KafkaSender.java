package com.hello.kafka;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@Slf4j
public class KafkaSender<T> {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	/**
	 * kafka 发送消息
	 *
	 * @param obj
	 *            消息对象
	 */
	public void send(T obj) {
		String jsonObj = JSON.toJSONString(obj);
		System.out.println("------------ message = {}"+jsonObj);
//		log.info("------------ message = {}", jsonObj);

		// 发送消息
		ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("member_log", jsonObj);
		future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
			@Override
			public void onFailure(Throwable throwable) {
				System.out.println("Produce: The message failed to be sent:" + throwable.getMessage());
//				log.info("Produce: The message failed to be sent:" + throwable.getMessage());
			}

			@Override
			public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
				// TODO 业务处理
				System.out.println("Produce: The message was sent successfully:");
				System.out.println("Produce: _+_+_+_+_+_+_+ result: " + stringObjectSendResult.toString());
//				log.info("Produce: The message was sent successfully:");
//				log.info("Produce: _+_+_+_+_+_+_+ result: " + stringObjectSendResult.toString());
			}
		});
	}
}