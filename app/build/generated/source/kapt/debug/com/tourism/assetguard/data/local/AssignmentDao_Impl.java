package com.tourism.assetguard.data.local;

import androidx.annotation.NonNull;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import com.tourism.assetguard.data.model.OfflineAssignment;
import java.lang.Class;
import java.lang.Long;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class AssignmentDao_Impl implements AssignmentDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<OfflineAssignment> __insertAdapterOfOfflineAssignment;

  private final EntityDeleteOrUpdateAdapter<OfflineAssignment> __updateAdapterOfOfflineAssignment;

  public AssignmentDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfOfflineAssignment = new EntityInsertAdapter<OfflineAssignment>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `offline_assignments` (`id`,`assetId`,`assetName`,`employeeId`,`employeeName`,`companyName`,`projectId`,`projectName`,`assignedDate`,`returnedDate`,`status`,`isPendingSync`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final OfflineAssignment entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getId());
        }
        if (entity.getAssetId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getAssetId());
        }
        if (entity.getAssetName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getAssetName());
        }
        if (entity.getEmployeeId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getEmployeeId());
        }
        if (entity.getEmployeeName() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getEmployeeName());
        }
        if (entity.getCompanyName() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getCompanyName());
        }
        if (entity.getProjectId() == null) {
          statement.bindNull(7);
        } else {
          statement.bindText(7, entity.getProjectId());
        }
        if (entity.getProjectName() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getProjectName());
        }
        statement.bindLong(9, entity.getAssignedDate());
        if (entity.getReturnedDate() == null) {
          statement.bindNull(10);
        } else {
          statement.bindLong(10, entity.getReturnedDate());
        }
        if (entity.getStatus() == null) {
          statement.bindNull(11);
        } else {
          statement.bindText(11, entity.getStatus());
        }
        final int _tmp = entity.isPendingSync() ? 1 : 0;
        statement.bindLong(12, _tmp);
      }
    };
    this.__updateAdapterOfOfflineAssignment = new EntityDeleteOrUpdateAdapter<OfflineAssignment>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `offline_assignments` SET `id` = ?,`assetId` = ?,`assetName` = ?,`employeeId` = ?,`employeeName` = ?,`companyName` = ?,`projectId` = ?,`projectName` = ?,`assignedDate` = ?,`returnedDate` = ?,`status` = ?,`isPendingSync` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final OfflineAssignment entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getId());
        }
        if (entity.getAssetId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getAssetId());
        }
        if (entity.getAssetName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getAssetName());
        }
        if (entity.getEmployeeId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getEmployeeId());
        }
        if (entity.getEmployeeName() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getEmployeeName());
        }
        if (entity.getCompanyName() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getCompanyName());
        }
        if (entity.getProjectId() == null) {
          statement.bindNull(7);
        } else {
          statement.bindText(7, entity.getProjectId());
        }
        if (entity.getProjectName() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getProjectName());
        }
        statement.bindLong(9, entity.getAssignedDate());
        if (entity.getReturnedDate() == null) {
          statement.bindNull(10);
        } else {
          statement.bindLong(10, entity.getReturnedDate());
        }
        if (entity.getStatus() == null) {
          statement.bindNull(11);
        } else {
          statement.bindText(11, entity.getStatus());
        }
        final int _tmp = entity.isPendingSync() ? 1 : 0;
        statement.bindLong(12, _tmp);
        if (entity.getId() == null) {
          statement.bindNull(13);
        } else {
          statement.bindText(13, entity.getId());
        }
      }
    };
  }

  @Override
  public Object insertAssignment(final OfflineAssignment assignment,
      final Continuation<? super Unit> $completion) {
    if (assignment == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfOfflineAssignment.insert(_connection, assignment);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object updateAssignment(final OfflineAssignment assignment,
      final Continuation<? super Unit> $completion) {
    if (assignment == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __updateAdapterOfOfflineAssignment.handle(_connection, assignment);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object getAllAssignments(final Continuation<? super List<OfflineAssignment>> $completion) {
    final String _sql = "SELECT * FROM offline_assignments ORDER BY assignedDate DESC";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _cursorIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _cursorIndexOfAssetId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assetId");
        final int _cursorIndexOfAssetName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assetName");
        final int _cursorIndexOfEmployeeId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "employeeId");
        final int _cursorIndexOfEmployeeName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "employeeName");
        final int _cursorIndexOfCompanyName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "companyName");
        final int _cursorIndexOfProjectId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "projectId");
        final int _cursorIndexOfProjectName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "projectName");
        final int _cursorIndexOfAssignedDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedDate");
        final int _cursorIndexOfReturnedDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "returnedDate");
        final int _cursorIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "status");
        final int _cursorIndexOfIsPendingSync = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isPendingSync");
        final List<OfflineAssignment> _result = new ArrayList<OfflineAssignment>();
        while (_stmt.step()) {
          final OfflineAssignment _item;
          final String _tmpId;
          if (_stmt.isNull(_cursorIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_cursorIndexOfId);
          }
          final String _tmpAssetId;
          if (_stmt.isNull(_cursorIndexOfAssetId)) {
            _tmpAssetId = null;
          } else {
            _tmpAssetId = _stmt.getText(_cursorIndexOfAssetId);
          }
          final String _tmpAssetName;
          if (_stmt.isNull(_cursorIndexOfAssetName)) {
            _tmpAssetName = null;
          } else {
            _tmpAssetName = _stmt.getText(_cursorIndexOfAssetName);
          }
          final String _tmpEmployeeId;
          if (_stmt.isNull(_cursorIndexOfEmployeeId)) {
            _tmpEmployeeId = null;
          } else {
            _tmpEmployeeId = _stmt.getText(_cursorIndexOfEmployeeId);
          }
          final String _tmpEmployeeName;
          if (_stmt.isNull(_cursorIndexOfEmployeeName)) {
            _tmpEmployeeName = null;
          } else {
            _tmpEmployeeName = _stmt.getText(_cursorIndexOfEmployeeName);
          }
          final String _tmpCompanyName;
          if (_stmt.isNull(_cursorIndexOfCompanyName)) {
            _tmpCompanyName = null;
          } else {
            _tmpCompanyName = _stmt.getText(_cursorIndexOfCompanyName);
          }
          final String _tmpProjectId;
          if (_stmt.isNull(_cursorIndexOfProjectId)) {
            _tmpProjectId = null;
          } else {
            _tmpProjectId = _stmt.getText(_cursorIndexOfProjectId);
          }
          final String _tmpProjectName;
          if (_stmt.isNull(_cursorIndexOfProjectName)) {
            _tmpProjectName = null;
          } else {
            _tmpProjectName = _stmt.getText(_cursorIndexOfProjectName);
          }
          final long _tmpAssignedDate;
          _tmpAssignedDate = _stmt.getLong(_cursorIndexOfAssignedDate);
          final Long _tmpReturnedDate;
          if (_stmt.isNull(_cursorIndexOfReturnedDate)) {
            _tmpReturnedDate = null;
          } else {
            _tmpReturnedDate = _stmt.getLong(_cursorIndexOfReturnedDate);
          }
          final String _tmpStatus;
          if (_stmt.isNull(_cursorIndexOfStatus)) {
            _tmpStatus = null;
          } else {
            _tmpStatus = _stmt.getText(_cursorIndexOfStatus);
          }
          final boolean _tmpIsPendingSync;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_cursorIndexOfIsPendingSync));
          _tmpIsPendingSync = _tmp != 0;
          _item = new OfflineAssignment(_tmpId,_tmpAssetId,_tmpAssetName,_tmpEmployeeId,_tmpEmployeeName,_tmpCompanyName,_tmpProjectId,_tmpProjectName,_tmpAssignedDate,_tmpReturnedDate,_tmpStatus,_tmpIsPendingSync);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getAssignmentsForEmployee(final String empId,
      final Continuation<? super List<OfflineAssignment>> $completion) {
    final String _sql = "SELECT * FROM offline_assignments WHERE employeeId = ?";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (empId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, empId);
        }
        final int _cursorIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _cursorIndexOfAssetId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assetId");
        final int _cursorIndexOfAssetName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assetName");
        final int _cursorIndexOfEmployeeId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "employeeId");
        final int _cursorIndexOfEmployeeName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "employeeName");
        final int _cursorIndexOfCompanyName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "companyName");
        final int _cursorIndexOfProjectId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "projectId");
        final int _cursorIndexOfProjectName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "projectName");
        final int _cursorIndexOfAssignedDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedDate");
        final int _cursorIndexOfReturnedDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "returnedDate");
        final int _cursorIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "status");
        final int _cursorIndexOfIsPendingSync = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isPendingSync");
        final List<OfflineAssignment> _result = new ArrayList<OfflineAssignment>();
        while (_stmt.step()) {
          final OfflineAssignment _item;
          final String _tmpId;
          if (_stmt.isNull(_cursorIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_cursorIndexOfId);
          }
          final String _tmpAssetId;
          if (_stmt.isNull(_cursorIndexOfAssetId)) {
            _tmpAssetId = null;
          } else {
            _tmpAssetId = _stmt.getText(_cursorIndexOfAssetId);
          }
          final String _tmpAssetName;
          if (_stmt.isNull(_cursorIndexOfAssetName)) {
            _tmpAssetName = null;
          } else {
            _tmpAssetName = _stmt.getText(_cursorIndexOfAssetName);
          }
          final String _tmpEmployeeId;
          if (_stmt.isNull(_cursorIndexOfEmployeeId)) {
            _tmpEmployeeId = null;
          } else {
            _tmpEmployeeId = _stmt.getText(_cursorIndexOfEmployeeId);
          }
          final String _tmpEmployeeName;
          if (_stmt.isNull(_cursorIndexOfEmployeeName)) {
            _tmpEmployeeName = null;
          } else {
            _tmpEmployeeName = _stmt.getText(_cursorIndexOfEmployeeName);
          }
          final String _tmpCompanyName;
          if (_stmt.isNull(_cursorIndexOfCompanyName)) {
            _tmpCompanyName = null;
          } else {
            _tmpCompanyName = _stmt.getText(_cursorIndexOfCompanyName);
          }
          final String _tmpProjectId;
          if (_stmt.isNull(_cursorIndexOfProjectId)) {
            _tmpProjectId = null;
          } else {
            _tmpProjectId = _stmt.getText(_cursorIndexOfProjectId);
          }
          final String _tmpProjectName;
          if (_stmt.isNull(_cursorIndexOfProjectName)) {
            _tmpProjectName = null;
          } else {
            _tmpProjectName = _stmt.getText(_cursorIndexOfProjectName);
          }
          final long _tmpAssignedDate;
          _tmpAssignedDate = _stmt.getLong(_cursorIndexOfAssignedDate);
          final Long _tmpReturnedDate;
          if (_stmt.isNull(_cursorIndexOfReturnedDate)) {
            _tmpReturnedDate = null;
          } else {
            _tmpReturnedDate = _stmt.getLong(_cursorIndexOfReturnedDate);
          }
          final String _tmpStatus;
          if (_stmt.isNull(_cursorIndexOfStatus)) {
            _tmpStatus = null;
          } else {
            _tmpStatus = _stmt.getText(_cursorIndexOfStatus);
          }
          final boolean _tmpIsPendingSync;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_cursorIndexOfIsPendingSync));
          _tmpIsPendingSync = _tmp != 0;
          _item = new OfflineAssignment(_tmpId,_tmpAssetId,_tmpAssetName,_tmpEmployeeId,_tmpEmployeeName,_tmpCompanyName,_tmpProjectId,_tmpProjectName,_tmpAssignedDate,_tmpReturnedDate,_tmpStatus,_tmpIsPendingSync);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getPendingAssignments(
      final Continuation<? super List<OfflineAssignment>> $completion) {
    final String _sql = "SELECT * FROM offline_assignments WHERE isPendingSync = 1";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _cursorIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _cursorIndexOfAssetId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assetId");
        final int _cursorIndexOfAssetName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assetName");
        final int _cursorIndexOfEmployeeId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "employeeId");
        final int _cursorIndexOfEmployeeName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "employeeName");
        final int _cursorIndexOfCompanyName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "companyName");
        final int _cursorIndexOfProjectId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "projectId");
        final int _cursorIndexOfProjectName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "projectName");
        final int _cursorIndexOfAssignedDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedDate");
        final int _cursorIndexOfReturnedDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "returnedDate");
        final int _cursorIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "status");
        final int _cursorIndexOfIsPendingSync = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isPendingSync");
        final List<OfflineAssignment> _result = new ArrayList<OfflineAssignment>();
        while (_stmt.step()) {
          final OfflineAssignment _item;
          final String _tmpId;
          if (_stmt.isNull(_cursorIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_cursorIndexOfId);
          }
          final String _tmpAssetId;
          if (_stmt.isNull(_cursorIndexOfAssetId)) {
            _tmpAssetId = null;
          } else {
            _tmpAssetId = _stmt.getText(_cursorIndexOfAssetId);
          }
          final String _tmpAssetName;
          if (_stmt.isNull(_cursorIndexOfAssetName)) {
            _tmpAssetName = null;
          } else {
            _tmpAssetName = _stmt.getText(_cursorIndexOfAssetName);
          }
          final String _tmpEmployeeId;
          if (_stmt.isNull(_cursorIndexOfEmployeeId)) {
            _tmpEmployeeId = null;
          } else {
            _tmpEmployeeId = _stmt.getText(_cursorIndexOfEmployeeId);
          }
          final String _tmpEmployeeName;
          if (_stmt.isNull(_cursorIndexOfEmployeeName)) {
            _tmpEmployeeName = null;
          } else {
            _tmpEmployeeName = _stmt.getText(_cursorIndexOfEmployeeName);
          }
          final String _tmpCompanyName;
          if (_stmt.isNull(_cursorIndexOfCompanyName)) {
            _tmpCompanyName = null;
          } else {
            _tmpCompanyName = _stmt.getText(_cursorIndexOfCompanyName);
          }
          final String _tmpProjectId;
          if (_stmt.isNull(_cursorIndexOfProjectId)) {
            _tmpProjectId = null;
          } else {
            _tmpProjectId = _stmt.getText(_cursorIndexOfProjectId);
          }
          final String _tmpProjectName;
          if (_stmt.isNull(_cursorIndexOfProjectName)) {
            _tmpProjectName = null;
          } else {
            _tmpProjectName = _stmt.getText(_cursorIndexOfProjectName);
          }
          final long _tmpAssignedDate;
          _tmpAssignedDate = _stmt.getLong(_cursorIndexOfAssignedDate);
          final Long _tmpReturnedDate;
          if (_stmt.isNull(_cursorIndexOfReturnedDate)) {
            _tmpReturnedDate = null;
          } else {
            _tmpReturnedDate = _stmt.getLong(_cursorIndexOfReturnedDate);
          }
          final String _tmpStatus;
          if (_stmt.isNull(_cursorIndexOfStatus)) {
            _tmpStatus = null;
          } else {
            _tmpStatus = _stmt.getText(_cursorIndexOfStatus);
          }
          final boolean _tmpIsPendingSync;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_cursorIndexOfIsPendingSync));
          _tmpIsPendingSync = _tmp != 0;
          _item = new OfflineAssignment(_tmpId,_tmpAssetId,_tmpAssetName,_tmpEmployeeId,_tmpEmployeeName,_tmpCompanyName,_tmpProjectId,_tmpProjectName,_tmpAssignedDate,_tmpReturnedDate,_tmpStatus,_tmpIsPendingSync);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object clearAssignments(final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM offline_assignments";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
