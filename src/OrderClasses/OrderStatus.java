package OrderClasses;

import java.util.List;

public enum OrderStatus {
    NEW,
    CONFIRMED,
    ASSIGNED,
    IN_TRANSIT,
    DELIVERED,
    CANCELLED;

    public List<OrderStatus> getNextStatuses() {
        return switch (this) {
            case NEW        -> List.of(CONFIRMED, CANCELLED);
            case CONFIRMED  -> List.of(ASSIGNED, CANCELLED);
            case ASSIGNED   -> List.of(IN_TRANSIT, CANCELLED);
            case IN_TRANSIT -> List.of(DELIVERED, CANCELLED);
            case DELIVERED  -> List.of();
            case CANCELLED  -> List.of();
        };
    }

    public boolean canTransitionTo(OrderStatus next) {
        return getNextStatuses().contains(next);
    }
}