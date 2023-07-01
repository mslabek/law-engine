import { Theme } from "@emotion/react/dist/emotion-react.cjs";
import React from "react";
import styled from "@emotion/styled";

function CustomNode({ data, selected }) {

    const NodeContainer = styled.div<{ theme?: Theme; selected: boolean }>(
        ({ theme, selected }) => ({
            padding: "10px",
            background: theme.colors.secondary300,
            color: theme.colors.primary,
            borderRadius: "5px",
            borderStyle: "solid",
            borderColor: "black",
            borderWidth: "1px",
            boxShadow: selected ? "0 0 0 0.5px #1a192b" : "0",
            width: "150px",
            display: "flex",
            justifyContent: "center",
        }),
    );

    return (
      <NodeContainer selected={selected}>
          <div>{data.label}</div>
      </NodeContainer>
    );
}

export default CustomNode;
