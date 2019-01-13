package com.syjun.demo.repository;

import com.syjun.demo.model.MeetingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : red_bull
 * @date : 2019. 1. 12.   PM 8:19
 */
public interface MeetingRoomRepository extends JpaRepository<MeetingRoom, Long>{

    MeetingRoom findOneById(long id);
}
