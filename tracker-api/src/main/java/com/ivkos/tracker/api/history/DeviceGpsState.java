package com.ivkos.tracker.api.history;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ivkos.tracker.core.models.device.Device;
import com.ivkos.tracker.core.models.gps.GpsState;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

import static java.time.OffsetDateTime.now;

@Entity
@Table(name = "gps_state", indexes = {
      @Index(columnList = "dateCreated"),
      @Index(columnList = "lastUpdatedTime"),
      @Index(columnList = "satelliteTime")
})
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public class DeviceGpsState
{
   @Id
   @GeneratedValue
   private UUID id;

   @Column(updatable = false)
   private OffsetDateTime dateCreated = now();

   @ManyToOne
   @JsonIgnore
   private Device device;

   @Embedded
   private GpsState state;

   public DeviceGpsState(Device device, GpsState state)
   {
      this.device = device;
      this.state = state;
   }
}
