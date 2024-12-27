package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Ticket {
    private static int idCounter = 0;
    private int rootNumber;
    private int place;
    private int price;
    private Date date;
    private boolean isValid;

    public Ticket(int place, int price, Date date) {
        this.rootNumber = ++idCounter;
        this.place = place;
        this.price = price;
        this.date = date;
        this.isValid = true;
    }

    public int getRootNumber() {
        return rootNumber;
    }

    public int getPlace() {
        return place;
    }

    public int getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }

    public boolean isValid() {
        return isValid;
    }

    public void invalidate() {
        this.isValid = false;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "rootNumber=" + rootNumber +
                ", place=" + place +
                ", price=" + price +
                ", date=" + date +
                ", isValid=" + isValid +
                '}';
    }
}

class TicketProvider {
    private List<Ticket> tickets = new ArrayList<>();

    public Ticket getTicket(int rootNumber) {
        for (Ticket ticket : tickets) {
            if (ticket.getRootNumber() == rootNumber) {
                return ticket;
            }
        }
        return null;
    }

    public boolean updateTicket(Ticket ticket) {
        // Логика обновления информации о билете
        Ticket existingTicket = getTicket(ticket.getRootNumber());
        if (existingTicket != null) {
            // Выполнить обновление здесь.
            return true;
        }
        return false;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }
}

class CashProvider {
    private String card;
    private boolean isAuthorized;
    private int authorizationId;

    public CashProvider(String card, boolean isAuthorized) {
        this.card = card;
        this.isAuthorized = isAuthorized;
    }

    public boolean authorize(int id) {
        this.isAuthorized = true;
        this.authorizationId = id;
        return true;
    }

    public boolean buy(String item) {
        if (isAuthorized) {
            // Логика покупки
            System.out.println("Purchasing " + item);
            return true;
        }
        return false;
    }
}

class Customer {
    private static int idCounter = 0;
    private int id;
    private List<Ticket> purchasedTickets;
    private float cash;

    public Customer(float cash) {
        this.id = ++idCounter;
        this.purchasedTickets = new ArrayList<>();
        this.cash = cash;
    }

    public void buyTicket(Ticket ticket, CashProvider cashProvider) {
        if (cash >= ticket.getPrice() && cashProvider.buy("Ticket " + ticket.getRootNumber())) {
            purchasedTickets.add(ticket);
            cash -= ticket.getPrice();
            System.out.println("Ticket " + ticket.getRootNumber() + " purchased successfully.");
        } else {
            System.out.println("Insufficient funds or authorization failed.");
        }
    }

    public List<Ticket> getPurchasedTickets() {

        return purchasedTickets;
    }

    public int getId() {
        return id;
    }
}

