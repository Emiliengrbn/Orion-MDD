import { Theme } from "./themes";

export interface Article {
    id: number;
    title: string;
    author: string; 
    theme: Theme;
    content: string;
    created_at: string;
}