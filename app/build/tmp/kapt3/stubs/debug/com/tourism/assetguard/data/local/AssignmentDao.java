package com.tourism.assetguard.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\u000f\u00a8\u0006\u0011"}, d2 = {"Lcom/tourism/assetguard/data/local/AssignmentDao;", "", "clearAssignments", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllAssignments", "", "Lcom/tourism/assetguard/data/model/OfflineAssignment;", "getAssignmentsForEmployee", "empId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPendingAssignments", "insertAssignment", "assignment", "(Lcom/tourism/assetguard/data/model/OfflineAssignment;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateAssignment", "app_debug"})
@androidx.room.Dao()
public abstract interface AssignmentDao {
    
    @androidx.room.Query(value = "SELECT * FROM offline_assignments ORDER BY assignedDate DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllAssignments(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.tourism.assetguard.data.model.OfflineAssignment>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM offline_assignments WHERE employeeId = :empId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAssignmentsForEmployee(@org.jetbrains.annotations.NotNull()
    java.lang.String empId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.tourism.assetguard.data.model.OfflineAssignment>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM offline_assignments WHERE isPendingSync = 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPendingAssignments(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.tourism.assetguard.data.model.OfflineAssignment>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertAssignment(@org.jetbrains.annotations.NotNull()
    com.tourism.assetguard.data.model.OfflineAssignment assignment, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateAssignment(@org.jetbrains.annotations.NotNull()
    com.tourism.assetguard.data.model.OfflineAssignment assignment, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM offline_assignments")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clearAssignments(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}