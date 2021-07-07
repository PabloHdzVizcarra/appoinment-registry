package com.patient.notifier;

import java.util.ArrayList;

public class EmailNotifierTestMock implements EmailNotifier
{

    ArrayList<Message> receivedMessages = new ArrayList<>();

    @Override
    public void sendNotification(String subject, String body, String address)
    {
        receivedMessages.add(new Message(address, subject, body));
    }

    public static class Message
    {
        public String toAddress;
        public String subject;
        public String body;

        public Message(String toAddress, String subject, String body)
        {
            this.toAddress = toAddress;
            this.subject = subject;
            this.body = body;
        }
    }
}
