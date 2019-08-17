package com.pim.importer.pimimpoter.entity;

public class Data
{
  private String name;
  private String description;
  private String provider;
  private boolean available;
  private String measurementUnits;

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public String getProvider()
  {
    return provider;
  }

  public void setProvider(String provider)
  {
    this.provider = provider;
  }

  public boolean isAvailable()
  {
    return available;
  }

  public void setAvailable(boolean available)
  {
    this.available = available;
  }

  public String getMeasurementUnits()
  {
    return measurementUnits;
  }

  public void setMeasurementUnits(String measurementUnits)
  {
    this.measurementUnits = measurementUnits;
  }
}
