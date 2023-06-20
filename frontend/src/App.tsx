import React from "react";

const App: React.FC = () => {
    return <div>{helloFunction()}</div>;
};

function helloFunction(): string {
    return "hello world";
}

export default App;
