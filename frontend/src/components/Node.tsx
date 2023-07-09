import { Theme } from "@emotion/react/dist/emotion-react.cjs";
import React, { useState } from "react";
import styled from "@emotion/styled";
import { Handle, Position, useUpdateNodeInternals } from "reactflow";
import AutorenewIcon from "@mui/icons-material/Autorenew";

const NodeContainer = styled.div<{ theme?: Theme; selected: boolean }>(({ theme, selected }) => ({
    padding: "10px",
    background: theme.colors.secondary400,
    color: theme.colors.primary,
    borderRadius: "5px",
    borderStyle: "solid",
    borderColor: "black",
    borderWidth: "1px",
    boxShadow: selected ? "0 0 0 0.5px #1a192b" : "0",
    width: "150px",
}));

const NodeHeader = styled.div({
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
});

const Button = styled.button({
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    margin: "0px",
    padding: "0px",
    width: "20px",
    height: "20px",
});

function CustomNode({ id, data, selected }) {
    const [sourcePosition, setSourcePosition] = useState(Position.Top);
    const [targetPosition, setTargetPosition] = useState(Position.Bottom);
    const updateNodeInternals = useUpdateNodeInternals();

    const swapHandles = () => {
        if (sourcePosition === Position.Top) {
            setSourcePosition(Position.Bottom);
            setTargetPosition(Position.Top);
        } else {
            setSourcePosition(Position.Top);
            setTargetPosition(Position.Bottom);
        }
        updateNodeInternals(id);
    };

    return (
        <NodeContainer selected={selected}>
            <NodeHeader>
                {data.label}
                <Button onClick={swapHandles} title="Switch handles">
                    <AutorenewIcon fontSize={"inherit"} />
                </Button>
            </NodeHeader>
            <Handle type="target" position={targetPosition} />
            <Handle type="source" position={sourcePosition} />
        </NodeContainer>
    );
}

export default CustomNode;
