package com.example.pallavigupta.epoque;

/**
 * Created by Rishabh on 29-Feb-16.
 */
public class Event_list_Element  {

    String Name;
    String Date;
    String Time;
    String Venue;
    int Max,Min;
    int Type;

    int ID;

    public Event_list_Element(String Time,String Name,String Date,String Venue,int ID,int Max,int Min,int Type)
    {
        this.Name=Name;
        this.Date=Date;
        this.Time=Time;
        this.Venue=Venue;
        this.ID=ID;
        this.Min=Min;
        this.Max=Max;
        this.Type=Type;
    }

    public String getName() {
        return Name;
    }

    public String getDate() {
        return Date;
    }

    public String getTime() {
        return Time;
    }

    public String getVenue() {
        return Venue;
    }

    public int getID() {
        return ID;
    }

    public int getMax() {
        return Max;
    }

    public int getMin() {
        return Min;
    }

    public int getType() {
        return Type;
    }
}
