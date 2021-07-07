package com.patient.notifier;


public class SmtpMessageSender implements EmailNotifier
{

    @Override
    public void sendNotification(String subject, String body, String address)
    {
        throw new IllegalArgumentException("Not Service Found");
    }
}
