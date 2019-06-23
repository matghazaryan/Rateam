package am.rate.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import am.rate.core.listeners.ISpinnerData;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Currency implements ISpinnerData {
    @JsonProperty("buy")
    private double buy;
    @JsonProperty("sell")
    private double sell;
    private String currency;

    public Currency(String currency) {
        this.currency = currency;
    }

    public double getBuy() {
        return buy;
    }

    public void setBuy(double buy) {
        this.buy = buy;
    }

    public double getSell() {
        return sell;
    }

    public void setSell(double sell) {
        this.sell = sell;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String aa(){
        return "usd1";
    }

    @Override
    public String getSpinnerText() {
        return currency;
    }

    @Override
    public String getSpinnerID() {
        return currency;
    }
}
