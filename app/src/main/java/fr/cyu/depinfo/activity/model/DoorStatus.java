package fr.cyu.depinfo.activity.model;

public enum DoorStatus {
    CLOSED(false),
    OPENED(true);

    private Boolean doorOpened;

    DoorStatus(Boolean doorOpened) {
        this.doorOpened = doorOpened;
    }

    public static DoorStatus fromDoorOpened(Boolean doorOpened) {
        if (doorOpened) {
            return OPENED;
        }
        return CLOSED;
    }

    public Boolean getDoorOpened() {
        return doorOpened;
    }
}
