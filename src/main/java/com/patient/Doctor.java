package com.patient;

public enum Doctor
{
    avery("Ralph Avery"),
    johnson("Beth Johnson"),
    murphy("Pat Murphy");

    private final String name;

    Doctor(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
