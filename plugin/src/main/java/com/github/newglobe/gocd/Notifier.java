package com.github.newglobe.gocd;

import com.github.newglobe.gocd.model.Message;

public interface Notifier {

    void sendMessage(String userEmail, Message message);

}
