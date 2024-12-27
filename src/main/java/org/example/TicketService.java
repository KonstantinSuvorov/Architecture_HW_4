package org.example;

import java.util.Date;

public class TicketService {
    public static void main(String[] args) {
        TicketProvider ticketProvider = new TicketProvider();
        CashProvider cashProvider = new CashProvider("1234-5678-9012-3456", false);
        Customer customer = new Customer(100.0f); // Клиент с 100 единицами наличных

        // Создаем и добавляем билеты
        Ticket ticket1 = new Ticket(1, 50, new Date());
        Ticket ticket2 = new Ticket(2, 75, new Date());
        ticketProvider.addTicket(ticket1);
        ticketProvider.addTicket(ticket2);

        // Авторизация через CashProvider
        cashProvider.authorize(customer.getId());

        // Клиент покупает билет
        customer.buyTicket(ticket1, cashProvider);
        customer.buyTicket(ticket2, cashProvider); // Этот не пройдет из-за недостатка средств
    }
}
