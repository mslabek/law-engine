import {
    Background,
    BackgroundVariant,
    ReactFlow,
    useNodesState,
} from "reactflow";
import "reactflow/dist/style.css";
import React from "react";
import CustomNode from "./Node";

const nodeTypes = {
    custom: CustomNode,
};

function Graph() {
    const proOptions = { hideAttribution: true };

    const [nodes, _, onNodesChange] = useNodesState(initialNodes);

    return (
        <div style={{ height: "100%", width: "100%" }}>
            <ReactFlow
                nodes={nodes}
                onNodesChange={onNodesChange}
                proOptions={proOptions}
                nodeTypes={nodeTypes}
            >
                <Background variant={BackgroundVariant.Dots} gap={12} size={1} />
            </ReactFlow>
        </div>
    );
}

export default Graph;

const initialNodes = [
    {
        id: '1',
        type: 'custom',
        data: {
            label: "Node 1"
        },
        position: { x: 0, y: 0 },
    },
    {
        id: '2',
        type: 'custom',
        data: {
            label: "Node 2"
        },
        position: { x: 250, y: 250 },
    },
    {
        id: '3',
        type: 'custom',
        data: {
            label: "Node 3"
        },
        position: { x: 0, y: 250 },
    },
    {
        id: '4',
        type: 'custom',
        data: {
            label: "Node 4"
        },
        position: { x: 250, y: 0 },
    },
];
