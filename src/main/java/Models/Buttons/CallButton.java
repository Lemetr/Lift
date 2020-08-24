package Models.Buttons;

import Interfaces.IButton;
import java.util.HashMap;
import java.util.Map;

public class CallButton implements IButton {

    private final Map<String, Integer> calls;

    public CallButton() {
        calls = new HashMap<>();
        calls.put("UP", 0);
        calls.put("DOWN", 0);
    }

    @Override
    public void addCall(String direction) {
        calls.replace(direction, getCalls(direction)+1);
    }

    @Override
    public void removeCall(String direction) {
        calls.replace(direction, getCalls(direction)-1);
    }

    @Override
    public int getCalls(String direction) {
        return calls.get(direction);
    }

    @Override
    public boolean isCalling() {
        for (Integer value : calls.values()) {
            if (value>0) return true;
        }
        return false;
    }
}