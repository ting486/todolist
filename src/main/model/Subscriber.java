package model;

public class Subscriber implements Observer {
    private SubscriberData subscriberData;
    private String updateMessage;

    public Subscriber(String name) {
        this.subscriberData = new SubscriberData(name);
    }

    public SubscriberData getSubscriberData() {
        return subscriberData;
    }

    @Override
    public void update(Item item) {

        updateMessage = this.subscriberData.getName()
                + " now knows this item has been added to SchoolList: " + item.printItem();
        System.out.println(updateMessage);


    }

    public String getUpdateMessage() {
        return updateMessage;
    }
}
