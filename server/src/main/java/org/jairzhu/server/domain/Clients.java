package org.jairzhu.server.domain;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class Clients {
    private volatile ConcurrentHashMap<String, Client> clients = new ConcurrentHashMap<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Clients)) return false;
        Clients clients1 = (Clients) o;
        return Objects.equals(clients, clients1.clients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clients);
    }

    public ConcurrentHashMap<String, Client> getClients() {
        return clients;
    }

    public void setClients(ConcurrentHashMap<String, Client> clients) {
        this.clients = clients;
    }
}
