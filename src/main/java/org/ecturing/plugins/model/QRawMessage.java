package org.ecturing.plugins.model;

public class QRawMessage {
    String address;
    String temperature;
    String humidity;

    Integer wind_power;

    public QRawMessage(String address, String temperature, String humidity, Integer wind_power) {
        this.address = address;
        this.temperature = temperature;
        this.humidity = humidity;
        this.wind_power = wind_power;
    }

    @Override
    public String toString() {
        return "位置：" + address + " \n温度：" + temperature  + " ℃\n湿度：" + humidity  + "%\n风力：" + wind_power+"级";
    }
}
