import { Background, BackgroundVariant, ReactFlow, useEdgesState, useNodesState } from "reactflow";
import "reactflow/dist/style.css";
import React from "react";
import CustomNode from "./Node";

const nodeTypes = {
    custom: CustomNode,
};

function Graph() {
    const proOptions = { hideAttribution: true };

    const [nodes, setNodes, onNodesChange] = useNodesState(initialNodes);
    const [edges, setEdges, onEdgesChange] = useEdgesState(initialEdges);

    return (
        <div style={{ height: "100%", width: "100%" }}>
            <ReactFlow
                nodes={nodes}
                edges={edges}
                onNodesChange={onNodesChange}
                onEdgesChange={onEdgesChange}
                nodeTypes={nodeTypes}
                proOptions={proOptions}
                fitView={true}
            >
                <Background variant={BackgroundVariant.Dots} gap={12} size={1} />
            </ReactFlow>
        </div>
    );
}

export default Graph;

const initialNodes = [
    {
        id: "1",
        type: "custom",
        data: {
            label: "Node 1",
        },
        position: { x: 0, y: 0 },
    },
    {
        id: "2",
        type: "custom",
        data: {
            label: "Node 2",
        },
        position: { x: 200, y: 0 },
    },
];

const initialEdges = [
    {
        id: "e1",
        source: "1",
        target: "2",
        type: "smoothstep",
    },
    {
        id: "e2",
        source: "2",
        target: "1",
        type: "smoothstep",
    },
];
