class OutOfStockException extends Exception {
    private final int shortfall;

    public OutOfStockException(String message, int shortfall) {
        super(message);
        this.shortfall = shortfall;
    }

    public int getShortfall() {
        return shortfall;
    }
}

class InvalidQuantityException extends Exception {
    public InvalidQuantityException(String message) {
        super(message);
    }
}

class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String message) {
        super(message);
    }
}

class Warehouse {
    private String[] items = {"Apple", "Banana", "Orange"};
    private int[] stock = {10, 5, 2};

    public void issue(String item, int qty) throws InvalidQuantityException, OutOfStockException, ItemNotFoundException {
        if (qty <= 0) {
            throw new InvalidQuantityException("Quantity must be greater than 0: " + qty);
        }

        int index = -1;
        for (int i = 0; i < items.length; i++) {
            if (items[i].equalsIgnoreCase(item)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new ItemNotFoundException("Item not found: " + item);
        }

        if (qty > stock[index]) {
            int shortfall = qty - stock[index];
            throw new OutOfStockException("Insufficient stock for " + item + ". Requested: " + qty + ", Available: " + stock[index], shortfall);
        }

        stock[index] -= qty;
        System.out.println("Issued " + qty + " " + item + "(s). Remaining: " + stock[index]);
    }
}

class Request {
    String item;
    int qty;

    Request(String item, int qty) {
        this.item = item;
        this.qty = qty;
    }
}

public class Stock {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();

        Request[] requests = {
            new Request("Apple", 3),
            new Request("Apple", -2),
            new Request("Banana", 10),
            new Request("Orange", 2),
            new Request("Orange", 1),
            new Request("Mango", 4)
        };

        for (Request req : requests) {
            try {
                warehouse.issue(req.item, req.qty);
            } catch (InvalidQuantityException e) {
                System.out.println("InvalidQuantity Error: " + e.getMessage());
            } catch (OutOfStockException e) {
                System.out.println("OutOfStock Error: " + e.getMessage() + " (Shortfall: " + e.getShortfall() + ")");
            } catch (ItemNotFoundException e) {
                System.out.println("ItemNotFound Error: " + e.getMessage());
            }
        }
    }
}