package com.dbaccess;

import java.sql.SQLException;
import java.util.List;

public interface OwnersHandler {
    public abstract int addOwner(Owner owner) throws SQLException;
    public abstract List<Owner> getAllOwners() throws SQLException;
}
