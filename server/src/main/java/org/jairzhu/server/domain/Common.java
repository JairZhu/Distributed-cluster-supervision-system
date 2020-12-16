package org.jairzhu.server.domain;

import org.jairzhu.server.mapper.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Common {
    public static final Clients clients = new Clients();
    public static RecordMapper recordMapper = null;
}
