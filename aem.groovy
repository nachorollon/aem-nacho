import org.apache.jackrabbit.oak.api.Type
import org.apache.jackrabbit.oak.commons.PathUtils
import org.apache.jackrabbit.oak.plugins.memory.ArrayBasedBlob
import org.apache.jackrabbit.oak.plugins.memory.PropertyStates
import org.apache.jackrabbit.oak.spi.commit.CommitInfo
import org.apache.jackrabbit.oak.spi.commit.EmptyHook
import org.apache.jackrabbit.oak.spi.state.ChildNodeEntry
import org.apache.jackrabbit.oak.spi.state.NodeBuilder
import org.apache.jackrabbit.oak.spi.state.NodeState
import org.apache.jackrabbit.oak.spi.state.NodeStateUtils
import org.apache.jackrabbit.oak.spi.state.NodeStore
 
updatedCheckpoint="5bc23ea8-ac42-4d3d-b790-0d8e266f789b";
indexLane = "async"
NodeBuilder childBuilder(NodeBuilder root, String path){
 NodeBuilder nb = root;
 for (String nodeName : PathUtils.elements(path)){
 nb = nb.child(nodeName);
 }
 return nb;
}
ns = session.store
indexPath = "/:async"
nodeState = NodeStateUtils.getNode(ns.root, indexPath)
println "Info $nodeState"
builder = ns.root.builder()
file = childBuilder(builder, indexPath)
file.setProperty(indexLane, updatedCheckpoint, Type.STRING)
ns.merge(builder, EmptyHook.INSTANCE, CommitInfo.EMPTY)
newNodeState = NodeStateUtils.getNode(ns.root, indexPath)
println "updated $newNodeState"
